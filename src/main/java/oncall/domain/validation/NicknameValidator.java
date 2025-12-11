package oncall.domain.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NicknameValidator {

    private static final int MIN_WORKERS = 5;
    private static final int MAX_WORKERS = 35;
    private static final int MAX_NAME_LENGTH = 5;

    private NicknameValidator() {
    }

    private static void validate(List<String> names){
        validateNullOrEmpty(names);
        validateSize(names);
        validateNames(names);
        validateDuplicate(names);
    }

    private static void validateNullOrEmpty(List<String> names){
        if(names == null || names.isEmpty()){
            throw new IllegalArgumentException("근무자 목록은 비어있을 수 없습니다.");
        }
    }

    private static void validateSize(List<String> names){
        int size = names.size();

        if (size < MIN_WORKERS || size > MAX_WORKERS){
            throw new IllegalArgumentException("근무자는 최소 5명, 최대 35명입니다.");
        }
    }

    private static void validateNames(List<String> names){
        for (String name : names){
            validateSingleName(name);
        }
    }

    private static void validateSingleName(String name){
        if(name == null){
            throw new IllegalArgumentException("닉네임은 null일 수 없습니다.");
        }

        String trimmed = name.trim();

        if(trimmed.isEmpty()){
            throw new IllegalArgumentException("닉네임은 공백일 수 없습니다.");
        }

        if (trimmed.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("닉네임은 최대 5자까지 가능합니다: " + trimmed);
        }
    }

    private static void validateDuplicate(List<String> names) {
        Set<String> seen = new HashSet<>();

        for(String name : names){
            String trimmed = name.trim();
            if (seen.contains(trimmed)) {
                throw new IllegalArgumentException("닉네임이 중복되었습니다: " + trimmed);
            }
            seen.add(trimmed);
        }
    }

}
