package com.ms.mscreate.server

import com.ms.mscreate.client.Client
import org.apache.tomcat.util.buf.HexUtils
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.io.InputStream
import java.io.PrintStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*


@Service
class Server {

    var port = 44405

    @Bean
    fun initialize() {

        var targetConnection: Socket? = null
        val client = Client()

        while (true) {
            val serverPort = ServerSocket(port)
            println("Servidor iniciado na porta $port")

            val serverClient: Socket = serverPort.accept()
            println("Conexão vinda do ip ${serverClient.inetAddress}")

            if (serverClient.isConnected) {
                targetConnection = client.initCliente()
                PrintStream(serverClient.getOutputStream()).println("")
            }

            val purePackets: InputStream = serverClient.getInputStream()
            val packets = Scanner(purePackets, "UTF-8")



            while (packets.hasNextLine()) {
                var currentLine = packets.nextLine()
                println("[Client] -> $currentLine")
                client.send(currentLine, targetConnection ?: serverClient)
                var packetStreamIn = client.recieve(targetConnection?:serverClient)
                if (packetStreamIn.hasNextLine()){
                    var packetStreamInLine = packetStreamIn.nextLine()
                    PrintStream(serverClient.getOutputStream()).println(packetStreamInLine)
                    println("[Client] <- $packetStreamInLine")
                }


            }
            packets.close()
            serverPort.close()
        }


    }
}