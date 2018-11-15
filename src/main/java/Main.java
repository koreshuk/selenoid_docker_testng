import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(final String[] args) throws Exception {
        Document document = Jsoup.connect("https://yandex.by/search/?text=jsoup&lr=10274&rnd=77630").get();

        Elements input = document.select(":containsOwn(HTML-файл)"); //needsclick   organic__url-text

        for (Element element : input) {
            System.out.println(input.text());

            
        }
    }
}
