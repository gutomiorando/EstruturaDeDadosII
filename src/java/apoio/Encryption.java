package apoio;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption 
{
    private static MessageDigest messageDigest = null; 

    public static String hash( String value )
    { 
        try
        {
            if ( value == null )
            {
                return null;
            }

            messageDigest = MessageDigest.getInstance( "SHA1" );

            return "<" + String.format( "%040x", new BigInteger( messageDigest.digest( value.getBytes() ) ).abs() ) + ">";
        }
        catch ( NoSuchAlgorithmException e )
        {
            System.out.println(e);
        }
        
        return value;
    }
}