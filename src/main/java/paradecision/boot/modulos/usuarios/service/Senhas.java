package paradecision.boot.modulos.usuarios.service;
import java.security.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;
public final class Senhas {
  private Senhas() {}
  public static String codificar(String senha) {byte[] sal=new byte[16];new SecureRandom().nextBytes(sal);return "pbkdf2$"+Base64.getEncoder().encodeToString(sal)+"$"+Base64.getEncoder().encodeToString(hash(senha,sal));}
  public static boolean conferir(String senha,String armazenada) {
    if(senha==null||armazenada==null)return false;
    if(!armazenada.startsWith("pbkdf2$"))return MessageDigest.isEqual(senha.getBytes(java.nio.charset.StandardCharsets.UTF_8),armazenada.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    try {var partes=armazenada.split("\\$");return MessageDigest.isEqual(hash(senha,Base64.getDecoder().decode(partes[1])),Base64.getDecoder().decode(partes[2]));}catch(IllegalArgumentException|IndexOutOfBoundsException e){return false;}
  }
  private static byte[] hash(String senha,byte[] sal) {try{var spec=new PBEKeySpec(senha.toCharArray(),sal,210000,256);try{return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}finally{spec.clearPassword();}}catch(GeneralSecurityException e){throw new IllegalStateException(e);}}
}
