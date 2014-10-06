package com.elsys.internetprogramming.sockets.sample;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		EchoServer server = new EchoServer();
		server.doTheMagic();

	}

}
