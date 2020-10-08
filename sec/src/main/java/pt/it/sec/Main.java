package pt.it.sec;


/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Test functions (for now!!)" );

        Assymetric assym = new Assymetric();
        Keypair keypair = new Keypair();
        Sign sign = new Sign();
        Symkey symkey = new Symkey();
        Symmetric symmetric = new Symmetric();
        Verifysign verifysign = new Verifysign();

        KeyPair pair = keypair.generateKeyPair();
        System.out.println( "I am the pair generated: "+pair);
    }
}
