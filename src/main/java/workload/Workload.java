package workload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import servlet.LoggingExtCall1;
import servlet.LoggingExtCall2;
import servlet.LoggingInternal;

public abstract class Workload {
	private Random rand = new Random();
	private Cipher cipher;
	private static SecretKeySpec secretKey;
	private static byte[] key;

	public Workload() {
		try {
			setKey(String.valueOf(System.currentTimeMillis()));
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	protected String callTo(String ipAndPort, boolean call1) throws UnsupportedEncodingException, IOException {
		long tic = System.nanoTime();
		URL url = new URL("http://" + ipAndPort + "/SyntheticComponents/index");
		String result = "";
		boolean first = true;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
			for (String line; (line = reader.readLine()) != null;) {
				if (first)
					first = false;
				else
					result += "\n";
				result += line;
			}
		}
		long toc = System.nanoTime();
		if (call1) 
			LoggingExtCall1.globalQueue.add(tic + "," + toc);
		else
			LoggingExtCall2.globalQueue.add(tic + "," + toc);
		return result;
	}

	protected double performNormalWork(double mean, double std) {
		double result = performConstantWork(rand.nextGaussian() * std + mean);
		return result;
	}

	protected double performExpWork(double mean) {
		double lambda = 1 / mean;
		double delay = Math.log(1 - rand.nextDouble()) / (-lambda);
		double result = performConstantWork(delay);
		return result;
	}

	protected double performBranchWork(double chance, double delayA, double delayB) {
		if (chance > rand.nextDouble()) {
			return performConstantWork(delayA);
		}
		return performConstantWork(delayB);
	}

	protected double performConstantWork(double milliseconds) {
		long start = System.nanoTime();
		byte[] encryptme;
		try {
			encryptme = String.valueOf(System.currentTimeMillis()).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			throw new IllegalStateException();
		}
		while (true) {
			try {
				encryptme = cipher.doFinal(encryptme);
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
				throw new IllegalStateException();
			} catch (BadPaddingException e) {
				e.printStackTrace();
				throw new IllegalStateException();
			}
			if (System.nanoTime() - start > milliseconds * 1000000)
				System.out.println(System.nanoTime() - start - milliseconds * 1000000);
				break;
		}
		long end = System.nanoTime();
		LoggingInternal.globalQueue.add((end - start) + "," + start + "," + end);
		return 1;
	}

	public abstract String performWork() throws UnsupportedEncodingException, IOException, InterruptedException;
}
