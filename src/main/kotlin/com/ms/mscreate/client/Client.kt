package com.ms.mscreate.client

import org.springframework.stereotype.Service
import java.io.IOException
import java.io.InputStream
import java.io.PrintStream
import java.net.Socket
import java.util.*

@Service
class Client {



    fun initCliente(): Socket {
       return Socket("localhost", 44406)
    }

    fun send(incommin: InputStream, target: Socket){

        var purePackets = target.getInputStream()
        val packets = Scanner(purePackets,"UTF-8")


        while (packets.hasNextLine()) {
            println("[Server] <- ${packets.nextLine()}")
            val saida = PrintStream(target.getOutputStream())
            saida.println(incommin)
            println(incommin.toString())
        }
        packets.close()
    }

}