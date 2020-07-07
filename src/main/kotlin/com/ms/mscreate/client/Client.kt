package com.ms.mscreate.client

import org.springframework.stereotype.Service
import java.io.PrintStream
import java.net.Socket
import java.util.*

@Service
class Client {

    fun initCliente(): Socket {
        println("Conectando ao servidor remoto ....")
        return Socket("211.43.146.215", 44405)
    }

    fun send(incommin: String, target: Socket){

        PrintStream(target.getOutputStream())
            .println(incommin)
            println("[Server] <- $incommin")


    }

    fun recieve(target: Socket): Scanner{

        val packetStreamIn = target.getInputStream()
        val packets = Scanner(packetStreamIn, "UTF-8")
        println("TESTE ${packets.nextLine()}")

        if (packets.hasNextLine()){
            println("[Server] -> ${packets.nextLine()}")
        }

        return packets

    }

}