import java.util.*;


public class WordFrequencyGame {
    private static final String SPACE_PATTEN = "\\s+";
    private static final String BLANK_SPACE = " ";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {

        try {
            List<WordInfo> list = calculateWordFrequencyResult(sentence);
            return generateWordFrequencyResult(list);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }

    }

    private List<WordInfo> calculateWordFrequencyResult(String sentence) {
        String[] words = sentence.split(SPACE_PATTEN);
        List<WordInfo> wordCounts = new ArrayList<>();
        for (String wordUnique : new HashSet<>(Arrays.asList(words))) {
            wordCounts.add(new WordInfo(wordUnique, (int) Arrays.stream(words).filter(word -> wordUnique.equals(word)).count()));
        }
        return wordCounts;
    }

    private String generateWordFrequencyResult(List<WordInfo> wordInfos) {
        wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

        StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String s = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
            wordFrequencyResult.add(s);
        }
        return wordFrequencyResult.toString();
    }

}
