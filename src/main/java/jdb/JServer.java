package jdb;

import java.io.IOException;
import rx.Observable;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import jdb.config.JdbConfig;

public class JServer {
	public JServer() {
		
	}

	
	public static void version() {
		Observable.just("Jdb server v=" + 
				   JdbConfig.VERSION)
				   .subscribe(s -> System.out.println(s));
		System.exit(0);
	}
	
	public static void usage() {
		Observable.just("Usage: -v or --version\n" + 
				"       -h or --help")
				   .subscribe(s -> System.out.println(s));
		System.exit(0);
	}
	
	public void init() {
		Signal.handle(new Signal("HUP"), new SignalHandler() {

			@Override
			public void handle(Signal arg0) {
				// do not nothing
				
			}
			
		});
		Signal.handle(new Signal("PIPE"), new SignalHandler() {

			@Override
			public void handle(Signal arg0) {
				// do not nothing
				
			}
			
		});
		
	}
	
	public static void daemonize() {
		try {
			System.in.close();
			System.out.close();
			System.err.close();
		} catch (IOException e) {
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		JServer server = new JServer();
		JServer.daemonize();
		server.init();
		
		if (args.length != 0) {
			if (args[0].equals("-v") ||
					args[0].equals("--version"))
				JServer.version();
			if (args[0].equals("-h") ||
					args[0].equals("--help"))
				JServer.usage();
		}
		

	}
}
