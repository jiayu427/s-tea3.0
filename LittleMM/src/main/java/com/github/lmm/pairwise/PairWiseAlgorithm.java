package com.github.lmm.pairwise;

import com.github.lmm.pairwise.core.Algorithm;
import com.github.lmm.pairwise.core.Combination;
import com.github.lmm.pairwise.core.CombinationList;
import com.github.lmm.pairwise.core.ParameterList;


import java.util.ArrayList;
import java.util.List;


public class PairWiseAlgorithm implements Algorithm {
        private List<Combination> combinations;
        private AllPairList allPairList;
        private CombinationCreator creator;

        public CombinationList generate(ParameterList parameters,
                        int maxCombinationLimit) {
                initializeAllPairs(parameters);
                int limit = (maxCombinationLimit < 0) ? parameters.getSize()
                                : maxCombinationLimit;
                for (int i = 0; i < limit; i++) {
                        Combination newCombination = creator.create();
                        if (newCombination == null) {
                                break;
                        }
                        combinations.add(newCombination);
                }
                return new CombinationList(combinations);
        }

        private void initializeAllPairs(ParameterList parameters) {
                combinations = new ArrayList<Combination>();
                allPairList = new AllPairList(parameters);
                creator = new CombinationCreator(allPairList);
        }
}