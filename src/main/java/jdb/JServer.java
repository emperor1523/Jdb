package jdb;

import java.io.IOException;
import rx.Observable;

import jdb.config.JdbConfig;

public class JServer {
	public void init() {
		
	}
	
	public static void version() {
//		System.out.println("Jdb server v=" + 
//						   JdbConfig.VERSION);
		Observable.just("Jdb server v=" + 
				   JdbConfig.VERSION)
				   .subscribe(s -> System.out.println(s));
		System.exit(0);
	}
	
	public static void usage() {
//		System.err.println("Usage: -v or --version");
//		System.err.println("       -h or --help");
		Observable.just("Usage: -v or --version\n" + 
				"       -h or --help")
				   .subscribe(s -> System.out.println(s));
		System.exit(0);
	}
	
	public static void main(String[] args) {
		JServer server = new JServer();
		server.init();
		
		if (args.length != 0) {
			if (args[0].equals("-v") ||
					args[0].equals("--version"))
				JServer.version();
			if (args[0].equals("-h") ||
					args[0].equals("--help"))
				JServer.usage();
		}
		

		Observable.just("+OK")
			.subscribe(s -> System.out.println(s));
	}
}
