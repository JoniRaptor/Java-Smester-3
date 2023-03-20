import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class BarCode extends Component {
    private static final int HEIGHT = 150;

    private static final int SIZE_WIDE = 6;
    private static final int SIZE_SLIM = 3;

    private static Map<Character, String> coding;

    static {
        coding = new HashMap<>();
        coding.put('0', "ssswwswss");
        coding.put('1', "wsswssssw");
        coding.put('2', "sswwssssw");
        coding.put('3', "wswwsssss");
        coding.put('4', "ssswwsssw");
        coding.put('5', "wsswwssss");
        coding.put('6', "sswwwssss");
        coding.put('7', "ssswsswsw");
        coding.put('8', "wsswsswss");
        coding.put('9', "sswwsswss");
        coding.put('A', "wsssswssw");
        coding.put('B', "sswsswssw");
        coding.put('C', "wswsswsss");
        coding.put('D', "sssswwssw");
        coding.put('E', "wssswwsss");
        coding.put('F', "sswswwsss");
        coding.put('G', "ssssswwsw");
        coding.put('H', "wsssswwss");
        coding.put('I', "sswsswwss");
        coding.put('J', "sssswwwss");
        coding.put('K', "wssssssww");
        coding.put('L', "sswssssww");
        coding.put('M', "wswssssws");
        coding.put('N', "sssswssww");
        coding.put('O', "wssswssws");
        coding.put('P', "sswswssws");
        coding.put('Q', "sssssswww");
        coding.put('R', "wssssswws");
        coding.put('S', "sswssswws");
        coding.put('T', "sssswswws");
        coding.put('U', "wwssssssw");
        coding.put('V', "swwsssssw");
        coding.put('W', "wwwssssss");
        coding.put('X', "swsswsssw");
        coding.put('Y', "wwsswssss");
        coding.put('Z', "swwswssss");
        coding.put('-', "swsssswsw");
        coding.put('.', "wwsssswss");
        coding.put(' ', "swwssswss");
        coding.put('*', "swsswswss");
        coding.put('$', "swswswsss");
        coding.put('/', "swswsssws");
        coding.put('+', "swssswsws");
        coding.put('%', "ssswswsws");
    }

    private String code;

    public String setCode(){
        if (this.code == "**"){
            return "";
        } else {
            return this.code.substring(1, this.code.length()-2);
        }
    }

    public BarCode() {
        this("");
    }

    public BarCode(String code) {
        super();

        setCode(code);
    }

    public String getCode() {
        if ("**".equals(code)) {
            return "";
        }

        return code.substring(1, code.length() - 2);
    }

    public void setCode(String code) {
        StringBuilder builder = new StringBuilder();
        builder.append('*');
        builder.append(code.toUpperCase());
        builder.append('*');

        this.code = builder.toString();

        revalidate();//damit Layout neu berechnet, mit neuen werten
        repaint();
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if ("**".equals(code)) {
            return;
        }

        g.setColor(Color.BLACK);


        int x = 0;
        int y = 0;

        for (int i = 0; i < code.length(); i++) {
            if (!coding.containsKey(code.charAt(i))) {
                System.err.println("Invalid character!");

                return;
            }

            for (int j = 0; j < 9; j++) {
                int width = coding.get(code.charAt(i)).charAt(j) == 'w' ? SIZE_WIDE : SIZE_SLIM;

                if (j%2 == 0) {
                    g.fillRect(x, y, width, getHeight());
                }

                x += width;
            }

            x += SIZE_SLIM;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if ("**".equals(code)) {
            return new Dimension(0, getHeight());
        }

        int width = (3 * SIZE_WIDE + 7 * SIZE_SLIM) * code.length() - SIZE_SLIM;

        return new Dimension(width, getHeight());
    }
}
