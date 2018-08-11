package by.epam.java_training.mihail_poliansky.final_project.util;

public class ExceptionStringCreator {
    public static String createString(Exception e){
        StringBuilder ans = new StringBuilder(e.getMessage());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            ans.append("\n").append(stackTraceElement);
        }
        return ans.toString();
    }
}
