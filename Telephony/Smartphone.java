package Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable{
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbersList, List<String> urlsList) {
        this.numbers = numbersList;
        this.urls = urlsList;
    }


    @Override
    public String browse(){
        StringBuilder builder = new StringBuilder();
        for (String url : this.urls) {
            char[] urlArray = url.toCharArray();

            boolean inValid = false;

            for (int i = 0; i < urlArray.length; i++) {
                if (Character.isDigit(urlArray[i])) {
                    builder.append("Invalid URL!\n") ;
                    inValid = true;
                    break;
                }
            }
            if (!inValid){
                builder.append(String.format("Browsing: %s!\n", url));
            }
        }
        return builder.toString();
    }

    @Override
    public String call() {
        StringBuilder builder = new StringBuilder();

        boolean invalid = false;

        for (String number : this.numbers) {
            char[] numArr = number.toCharArray();
            for (int i = 0; i < numArr.length; i++) {
                if (Character.isLetter(numArr[i])){
                    builder.append("Invalid number!\n") ;
                    invalid = true;
                    break;
                }
            }
            if (!invalid){
                builder.append(String.format("Calling... %s\n", number));
            }

        }
        return builder.toString();
    }
}
