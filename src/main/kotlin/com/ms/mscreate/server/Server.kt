package com.ms.mscreate.server

import com.ms.mscreate.client.Client
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.io.InputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*


@Service
class Server {

    var port = 44405

    @Bean
    fun initialize() {

        var targetConnection: Socket? = null

        while (true){
            val serverPort = ServerSocket(port)
            println("Servidor iniciado na porta $port")

            val serverClient: Socket = serverPort.accept()
            println("Conexão vinda do ip ${serverClient.inetAddress}")
            if (serverClient.isConnected){
                targetConnection = Client().initCliente()
                println("Iniciando conexão com servidor remoto...")
            }


            val purePackets: InputStream = serverClient.getInputStream()
            val packets = Scanner(purePackets,"UTF-8")

//            val bytAr: ByteArray = client.getInputStream().


            while (packets.hasNextLine()) {
                println("[Client] -> ${packets.nextLine()}")
//                val saida = PrintStream(client.getOutputStream())
//                saida.println("asd")
                Client().send(purePackets,Client().initCliente())


            }
            packets.close()
            serverPort.close()
        }



    }
}