public class AES_TEST
{
    // Attribute
    static byte[] txt = {'H', 'a', 'l', 'l', 'i', ' ', 'H', 'a', 'l', 'l', 'o', ' ', 'h', 'a', 's', 't'};
    static byte[] key = {'H', 'H', 'a', 'a', 'l', 'l', 'l', 'l', 'o', 'o', ' ', ' '};
    // Konstruktor
    private AES_TEST()
    {
    }

    // Methoden
    public static void test()
    {
        byte[] bob = AES.encrypt(txt, key);
        for(int i = 0; i < bob.length; i++)
            System.out.println(bob[i]);
    }

}
