package services;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ExecComands {

	private static final Logger log = Logger.getLogger(ExecComands.class.getName());

	public static void executeCommand(final String command) throws IOException {

		System.setProperty("user.dir", "C:\\Users\\gustavo.santos\\Desktop\\Projetos\\tangerino-web");
		
		final ArrayList<String> commands = new ArrayList<String>();
		commands.add(command);

		BufferedReader br = null;

		try {
			final ProcessBuilder p = new ProcessBuilder(commands);
			final Process process = p.start();
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("Retorno do comando = [" + line + "]");
			}
		} catch (IOException ioe) {
			log.severe("Erro ao executar comando shell" + ioe.getMessage());
			throw ioe;
		} finally {
			secureClose(br);
		}
	}

	private static void secureClose(final Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException ex) {
			log.severe("Erro = " + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			executeCommand("git pull");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

