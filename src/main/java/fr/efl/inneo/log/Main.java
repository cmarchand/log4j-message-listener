package fr.efl.inneo.log;

public class Main {

	public static void main(String[] args) {
		String log = args[0];
		XProcMessageListener msgListener = new XProcMessageListener();
		msgListener.info(null, null, log);
	}

}
