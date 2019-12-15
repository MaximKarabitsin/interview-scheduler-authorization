package com.netcracker.interviewschedulerauthorization.intities.enums;

import java.util.Arrays;
import java.util.List;

public enum CombineAlgorithm {

    PERMIT_IF_ALL_PERMITTED {
        @Override
        public boolean combine(List<Boolean> results) {
            if (isEmpty(results)) return false;
            return !results.contains(false);
        }
    }, PERMIT_IF_2_PERMITTED {
        @Override
        public boolean combine(List<Boolean> results) {
            if (isEmpty(results)) return false;
            boolean hasTrue = false;
            for (Boolean result : results) {
                if (result) {
                    if (hasTrue) return true;
                    hasTrue = true;
                }
            }
            return false;
        }
    }, DENY_IF_NOT_PERMITTED {
        @Override
        public boolean combine(List<Boolean> results) {
            if (isEmpty(results)) return false;
            return results.contains(true);
        }
    };

    abstract public boolean combine(List<Boolean> results);

    boolean isEmpty(List<Boolean> results) {
        return (results == null || results.isEmpty());
    }
}
