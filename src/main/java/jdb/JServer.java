package jdb;

import java.io.IOException;
import rx.Observable;
import java.lang.management.ManagementFactory;

import jdb.config.JdbConfig;

public class JServer {
	private int pid;
	private long port;
	public JServer() {
		port = JdbConfig.SERVERPORT;
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
		pid = Integer.valueOf(ManagementFactory
				.getRuntimeMXBean()
				.getName()
				.split("@")[0]);
		
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
		
		if (args.length != 0) {
			if (args[0].equals("-v") ||
					args[0].equals("--version"))
				JServer.version();
			if (args[0].equals("-h") ||
					args[0].equals("--help"))
				JServer.usage();
		}
		server.init();

	}
}
