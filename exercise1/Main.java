/**
 *
 *  @author Soszyński Artur S23632
 *
 */

package zad1;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
	  Function<String, List<String>> flines = (path -> {
          ArrayList<String> list = new ArrayList<>();
          String line;
          try(BufferedReader br = new BufferedReader(new FileReader(path.toString()))){
              while ((line = br.readLine()) != null){
                  list.add(line);
              }
          } catch (IOException e){
              System.out.println(e);
          }
          return list;
      });
      Function<List<String>, String> join = (list -> {
         StringBuilder sb = new StringBuilder();
         for (String s : list){
             sb.append(s);
         }
         return sb.toString();
      });
      Function<String, List<Integer>> collectInts = (text -> {
          ArrayList<Integer> listOfInts = new ArrayList<>();
          Pattern p = Pattern.compile("\\d+");
          Matcher m = p.matcher(text);
          while (m.find()){
              listOfInts.add(Integer.parseInt(m.group()));
          }
          return listOfInts;
      });
      Function<List<Integer>, Integer> sum = (list -> {
          int sumOfInts = 0;
          for (Integer i : list){
              sumOfInts += i;
          }
          return sumOfInts;
      });
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
