package com.ms.mscreate.client

import org.springframework.stereotype.Service
import java.io.PrintStream
import java.net.Socket

@Service
class Client {

    fun initCliente(): Socket {
        println("Conectando ao servidor remoto ....")
        return Socket("localhost", 44406)
    }

    fun send(incommin: String, target: Socket){

        PrintStream(target.getOutputStream())
            .println(incommin)
            println("[Server] <- $incommin")


    }

}