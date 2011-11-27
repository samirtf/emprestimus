package iu.web.server.sistema.utilitarios;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia implements Serializable{

	private static String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

	/**
	 * Gera um codigo Hash
	 * 
	 * @param String
	 * 		frase
	 * @param String
	 * 		algoritmo
	 * 
	 * @return byte[]
	 */
	public static byte[] gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/**
	 * Gera uma string criptografada com o podelo MD5
	 * 
	 * @param String
	 * 		login do usuario
	 * @param String
	 * 		senha do usuario
	 * 
	 * @return String
	 * 		Texto criptografado
	 * 
	 * @throws Exception
	 */
	public static String criptografaMD5( String login, String senha ) throws Exception {
		return stringHexa(gerarHash(login+"emprestimusalt"+senha, "MD5"));
	}

}
