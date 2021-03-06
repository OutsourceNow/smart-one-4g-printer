package com.dm.one.utils;

import android.annotation.SuppressLint;

/**
 * @author admin
 */
@SuppressLint("UseValueOf")
public class ByteUtil {
	private ByteUtil() {
        System.out.println("ByteUtil Constructor");
    }

	public static byte byteToHex(byte arg) {
		byte hex = 0;
		if (arg >= 48 && arg <= 57) {
			hex = (byte) (arg - 48);
		} else if (arg >= 65 && arg <= 70) {
			hex = (byte) (arg - 55);
		} else if (arg >= 97 && arg <= 102) {
			hex = (byte) (arg - 87);
		}
		return hex;
	}

	private byte[] stringToHex(String data) {
		byte[] temp = data.getBytes();
		byte[] result = new byte[temp.length / 2];
		for (int i = 0; i < result.length; i++) {
			result[i] = (byte) (byteToHex(temp[i * 2]) << 4 | byteToHex(temp[i * 2 + 1]));
		}
		return result;
	}

	/**
	 * 返回byte[] 数组实际长度
	 * @param data
	 * @return
	 */
	public static int returnActualLength(byte[] data) {
		int i = 0;
		for (; i < data.length; i++) {
			if (data[i] == '\0') {
				break;
			}
		}
		return i;
	}

	public static String byte2String(byte[] byteArray) {
		char[] result = new char[byteArray.length * 2];
		for (int i = 0; i < byteArray.length; i++) {
			char temp = (char) (((byteArray[i] & 0xf0) >> 4) & 0x0f);
			result[i * 2] = (char) (temp > 9 ? 'A' + temp - 10 : '0' + temp);
			temp = (char) (byteArray[i] & 0x0f);
			result[i * 2 + 1] = (char) (temp > 9 ? 'A' + temp - 10 : '0' + temp);
		}
		return new String(result);
	}

	public static String bytesToString(byte[] b) {
		StringBuffer result = new StringBuffer("");
		int length = b.length;

		for (int i = 0; i < length; i++) {
			char ch = (char)(b[i] & 0xff);
			if (ch == 0) {
				break;
			}
			
			result.append(ch);
		}

		return result.toString();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
	public static String bytearrayToHexString(byte[] b, int leng) {
		StringBuffer strbuf = new StringBuffer();

		for (int i = 0; i < leng; i++) {
			strbuf.append("0123456789ABCDEF".charAt(((byte) ((b[i] & 0xf0) >> 4))));
			strbuf.append("0123456789ABCDEF".charAt((byte) (b[i] & 0x0f)));
			strbuf.append(" ");
		}
		return strbuf.toString();
	}

	/**
	 *
	 * @Functon byteArrayToHexstring
	 * @Description byte数组转十六进制字串
	 * @param
	 * @Return 转换结果String
	 */
	public static String byteArrayToHexstring(byte[] bytes)
	{
		StringBuilder hexString = new StringBuilder();

		if (bytes.length <= 0 || bytes == null) {
			return null;
		}

		String hv;
		int v = 0;

		for (int i = 0; i < bytes.length; i++) {
			v = bytes[i] & 0xFF;
			hv = Integer.toHexString(v);

			if (hv.length() < 2) {
				hexString.append(0);
			}

			hexString.append(hv);
		}

		return hexString.toString().toUpperCase();
	}

	public static byte[] stringToBytes(String s) {
		return s.getBytes();
	}
	
	public static void shortToBytes(byte[] b, short x, int offset) {
		//byte[] b = new byte[2];
		
		if (b.length-offset >= 2) {
			b[offset + 1] = (byte) (x >> 8);
			b[offset + 0] = (byte) (x >> 0);
		}
		
		//return b;
	}

	public static short bytesToShort(byte[] b, int offset) {
		short x = 0;
		if (b.length-offset >= 2) {
			x = (short) (((b[offset + 1] << 8) | b[offset + 0] & 0xff));
		}
		
		return x;
	}
	
	public static String byteToHexString(byte b) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("0123456789ABCDEF".charAt((int) ((b >> 4) & 0x0F)));
		sbBuffer.append("0123456789ABCDEF".charAt((int) (b & 0x0F)));
		return sbBuffer.toString();
	}

	public static void intToBytes(byte[] b, int x, int offset) {
		//byte[] b = new byte[4];
		
		if (b.length-offset >= 4) {
			b[offset + 3] = (byte) (x >> 24);
			b[offset + 2] = (byte) (x >> 16);
			b[offset + 1] = (byte) (x >> 8);
			b[offset + 0] = (byte) (x >> 0);
		}
		
		//return b;
	}

	public static int bytesToInt(byte[] src) {
		int value = src[0] & 255 | (src[1] & 255) << 8;
		return value;
	}

	public static int bytesToInt(byte[] b, int offset) {
		int x = 0;
		if (b.length-offset >= 4) {
			x = (int) ((((b[offset + 3] & 0xff) << 24)
					| ((b[offset + 2] & 0xff) << 16)
					| ((b[offset + 1] & 0xff) << 8) | ((b[offset + 0] & 0xff) << 0)));
		}
		
		return x;
	}

	public static void longToBytes(byte[] b, long x, int offset) {
		//byte[] b = new byte[8];
		
		if (b.length-offset >= 8) {
			b[offset + 7] = (byte) (x >> 56);
			b[offset + 6] = (byte) (x >> 48);
			b[offset + 5] = (byte) (x >> 40);
			b[offset + 4] = (byte) (x >> 32);
			b[offset + 3] = (byte) (x >> 24);
			b[offset + 2] = (byte) (x >> 16);
			b[offset + 1] = (byte) (x >> 8);
			b[offset + 0] = (byte) (x >> 0);
		}
		
		//return b;
	}

	public static long bytesToLong(byte[] b, int offset) {
		long x = 0;
		if (b.length-offset >= 8) {
			x = ((((long) b[offset + 7] & 0xff) << 56)
					| (((long) b[offset + 6] & 0xff) << 48)
					| (((long) b[offset + 5] & 0xff) << 40)
					| (((long) b[offset + 4] & 0xff) << 32)
					| (((long) b[offset + 3] & 0xff) << 24)
					| (((long) b[offset + 2] & 0xff) << 16)
					| (((long) b[offset + 1] & 0xff) << 8) | (((long) b[offset + 0] & 0xff) << 0));
		}
		
		return x;
	}

	public static void charToBytes(byte[] b, char ch, int offset) {
		// byte[] b = new byte[2];
		
		if (b.length-offset >= 2) {
			int temp = (int) ch;
			for (int i = 0; i < 2; i ++ ) {
				b[offset + i] = new Integer(temp & 0xff).byteValue();
				temp = temp >> 8;
			}
		}
	}

	public static char bytesToChar(byte[] b, int offset) {
		int s = 0;
		
		if (b.length-offset >= 2) {
			if (b[offset + 1] > 0) {
				s += b[offset + 1];
			} else {
				s += 256 + b[offset + 0];
			}
			s *= 256;
			if (b[offset + 0] > 0) {
				s += b[offset + 1];
			} else {
				s += 256 + b[offset + 0];
			}
		}
		
		char ch = (char) s;
		return ch;
	}

	public static void floatToBytes(byte[] b, float x, int offset) {
		//byte[] b = new byte[4];
		
		if (b.length-offset >= 4) {
			int l = Float.floatToIntBits(x);
			for (int i = 0; i < 4; i++) {
				b[offset + i] = new Integer(l).byteValue();
				l = l >> 8;
			}
		}
		
		//return b;
	}

	public static float bytesToFloat(byte[] b, int offset) {
		int l = 0;
		
		if (b.length-offset >= 4) {
			l = b[offset + 0];
			l &= 0xff;
			l |= ((long) b[offset + 1] << 8);
			l &= 0xffff;
			l |= ((long) b[offset + 2] << 16);
			l &= 0xffffff;
			l |= ((long) b[offset + 3] << 24);
		}
		
		return Float.intBitsToFloat(l);
	}

	public static void doubleToBytes(byte[] b, double x, int offset) {
		//byte[] b = new byte[8];
		
		if (b.length-offset >= 8) {
			long l = Double.doubleToLongBits(x);
			for (int i = 0; i < 4; i++) {
				b[offset + i] = new Long(l).byteValue();
				l = l >> 8;
			}
		}
		
		//return b;
	}

	public static double bytesToDouble(byte[] b, int offset) {
		long l = 0;
		
		if (b.length-offset >= 8) {
			l = b[0];
			l &= 0xff;
			l |= ((long) b[1] << 8);
			l &= 0xffff;
			l |= ((long) b[2] << 16);
			l &= 0xffffff;
			l |= ((long) b[3] << 24);
			l &= 0xffffffffL;
			l |= ((long) b[4] << 32);
			l &= 0xffffffffffL;
			l |= ((long) b[5] << 40);
			l &= 0xffffffffffffL;
			l |= ((long) b[6] << 48);
			l &= 0xffffffffffffffL;
			l |= ((long) b[7] << 56);
		}
		
		return Double.longBitsToDouble(l);
	}
	
	/**
	  * 锟斤拷short转为锟斤拷锟街斤拷锟斤拷前锟斤拷锟斤拷锟街斤拷锟节猴拷
	  */
	public static short toLh(short n) {
	  byte[] b = new byte[2];
	  b[0] = (byte) (n & 0xff);
	  b[1] = (byte) (n >> 8 & 0xff);
	  
	  short ret = bytesToShort(b, 0);
	  return ret;
	}
	
	/** 
	  * 锟斤拷short转为锟斤拷锟街斤拷锟斤拷前锟斤拷锟斤拷锟街斤拷锟节猴拷
	  */
	public static short toHl(short n) {
	  byte[] b = new byte[2];
	  b[1] = (byte) (n & 0xff);
	  b[0] = (byte) (n >> 8 & 0xff);
	  
	  short ret = bytesToShort(b, 0);
	  return ret;
	}
	
	/**
	  * 锟斤拷int转为锟斤拷锟街斤拷锟斤拷前锟斤拷锟斤拷锟街斤拷锟节猴拷
	  */
	public static int toLh(int n) {
	  byte[] b = new byte[4];
	  b[0] = (byte) (n & 0xff);
	  b[1] = (byte) (n >> 8 & 0xff);
	  b[2] = (byte) (n >> 16 & 0xff);
	  b[3] = (byte) (n >> 24 & 0xff);
	  
	  int ret = bytesToInt(b, 0);
	  return ret;
	}
	  
	/**
	  * 锟斤拷int转为锟斤拷锟街斤拷锟斤拷前锟斤拷锟斤拷锟街斤拷锟节猴拷
	  */
	public static int toHl(int n) {
	  byte[] b = new byte[4];
	  b[3] = (byte) (n & 0xff);
	  b[2] = (byte) (n >> 8 & 0xff);
	  b[1] = (byte) (n >> 16 & 0xff);
	  b[0] = (byte) (n >> 24 & 0xff);
	  
	  int ret = bytesToInt(b, 0);
	  return ret;
	}
	
	/**
	  * 锟斤拷long转为锟斤拷锟街斤拷锟斤拷前锟斤拷锟斤拷锟街斤拷锟节猴拷
	  */
	public static long toLh(long n) {
	  byte[] b = new byte[8];
	  b[0] = (byte) (n & 0xff);
	  b[1] = (byte) (n >> 8 & 0xff);
	  b[2] = (byte) (n >> 16 & 0xff);
	  b[3] = (byte) (n >> 24 & 0xff);
	  b[4] = (byte) (n >> 32 & 0xff);
	  b[5] = (byte) (n >> 40 & 0xff);
	  b[6] = (byte) (n >> 48 & 0xff);
	  b[7] = (byte) (n >> 56 & 0xff);
	  
	  long ret = bytesToLong(b, 0);
	  return ret;
	}
	  
	/**
	  * 锟斤拷long转为锟斤拷锟街斤拷锟斤拷前锟斤拷锟斤拷锟街斤拷锟节猴拷
	  */
	public static long toHl(long n) {
	  byte[] b = new byte[8];
	  b[7] = (byte) (n & 0xff);
	  b[6] = (byte) (n >> 8 & 0xff);
	  b[5] = (byte) (n >> 16 & 0xff);
	  b[4] = (byte) (n >> 24 & 0xff);
	  b[3] = (byte) (n >> 32 & 0xff);
	  b[2] = (byte) (n >> 40 & 0xff);
	  b[1] = (byte) (n >> 48 & 0xff);
	  b[0] = (byte) (n >> 56 & 0xff);
	  
	  long ret = bytesToLong(b, 0);
	  return ret;
	}

	/**
	  * 锟斤拷byte[]锟斤拷锟斤拷锟斤拷锟斤拷锟叫憋拷锟斤拷(锟斤拷实锟斤拷锟斤拷锟捷筹拷锟饺存储锟斤拷锟街斤拷锟斤拷锟斤拷前2锟斤拷锟街斤拷)
	  */
	public static void encodeOutputBytes(byte[] b, short sLen) {
		if (b.length >= sLen+2) {
			System.arraycopy(b, 0, b, 2, sLen);
			byte[] byShort = new byte[2];
			shortToBytes(byShort, sLen, 0);
			System.arraycopy(byShort, 0, b, 0, byShort.length);
		}
	}
	
	/**
	  * 锟斤拷byte[]锟斤拷锟斤拷锟斤拷锟斤拷锟叫凤拷锟斤拷锟斤拷(锟斤拷实锟斤拷锟斤拷锟捷筹拷锟斤拷锟皆凤拷锟斤拷值锟斤拷式锟斤拷锟斤拷)
	  */
	public static short decodeOutputBytes(byte[] b) {
		byte[] byShort = new byte[2];
		System.arraycopy(b, 0, byShort, 0, byShort.length);
		short sLen = bytesToShort(byShort, 0);
		
		System.arraycopy(b, 2, b, 0, sLen);
		
		return sLen;
	}

	public static String hexStr2Str(String hexStr)
	{
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++)
		{
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
}