import java.util.*;


public class WordFrequencyGame {
    private static final String SPACE_PATTEN = "\\s+";
    private static final String BLANK_SPACE = " ";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_PATTEN).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> list = CalculateWordFrequencyResult(sentence);
                return generateWordFrequencyResult(list);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordInfo> CalculateWordFrequencyResult(String sentence) {
        String[] words = sentence.split(SPACE_PATTEN);
        List<WordInfo> wordCounts = new ArrayList<>();
        for (String wordUnique : new HashSet<>(Arrays.asList(words))) {
            wordCounts.add(new WordInfo(wordUnique, (int) Arrays.stream(words).filter(word -> wordUnique.equals(word)).count()));
        }
        return wordCounts;
    }

    private String generateWordFrequencyResult(List<WordInfo> list) {
        list.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

        StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : list) {
            String s = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
