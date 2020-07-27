import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class WordFrequencyGame {
    private static final String SPACE_PATTEN = "\\s+";
    private static final String BLANK_SPACE = " ";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_PATTEN).length==1) {
            return sentence + " 1";
        } else {

            try {

                String[] words = sentence.split(SPACE_PATTEN);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfos.add(wordInfo);
                }

                Map<String, List<WordInfo>> wordInfoMap =getListMap(wordInfos);

                List<WordInfo> list = new ArrayList<>();

                return generateWordFrequencyResult(wordInfoMap, list);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private String generateWordFrequencyResult(Map<String, List<WordInfo>> map, List<WordInfo> list) {
        List<WordInfo> infos;
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        infos = list;

        infos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

        StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : infos) {
            String s = wordInfo.getValue() + BLANK_SPACE +wordInfo.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
