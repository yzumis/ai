package com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.network;

import com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.filters.Convolutional2DFilter;
import com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.filters.Input2DFilter;
import com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.filters.MaxPooling2DFilter;
import com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.filters.Relu2DFilter;
import com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.input.Input2D;
import com.yzumis.ia.genetic.Reproducible;

import java.util.ArrayList;
import java.util.List;

public class ConvReluPooling2DNeuron implements Input2DFilter, Reproducible {

    private final List<Input2DFilter> input2DFilters;

    public ConvReluPooling2DNeuron(final List<Input2DFilter> input2DFilters) {
        this.input2DFilters = input2DFilters;
    }

    public ConvReluPooling2DNeuron(final int filterSize) {
        final Convolutional2DFilter convolutional2DFilter = new Convolutional2DFilter(filterSize);
        final Relu2DFilter relu2DFilter = new Relu2DFilter();
        final MaxPooling2DFilter maxPooling2DFilter = new MaxPooling2DFilter(filterSize, filterSize);
        this.input2DFilters = new ArrayList<>();
        this.input2DFilters.add(convolutional2DFilter);
        this.input2DFilters.add(relu2DFilter);
        this.input2DFilters.add(maxPooling2DFilter);
    }


    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final List<Input2DFilter> input2DFiltersChild = new ArrayList<>();
        final ConvReluPooling2DNeuron convReluPooling2DNeuron = (ConvReluPooling2DNeuron) reproducible;
        for(int i = 0; i < this.input2DFilters.size(); i++) {
            final Input2DFilter input2DFilterA = this.input2DFilters.get(i);
            final Input2DFilter input2DFilterB = convReluPooling2DNeuron.input2DFilters.get(i);
            if((input2DFilterA instanceof Reproducible) && (input2DFilterB instanceof Reproducible)) {
                final Reproducible reproducibleA = (Reproducible) input2DFilterA;
                final Reproducible reproducibleB = (Reproducible) input2DFilterB;
                final Input2DFilter input2DFilterChild = (Input2DFilter) reproducibleA.reproduce(reproducibleB, mutationRate);
                input2DFiltersChild.add(input2DFilterChild);
            } else {
                input2DFiltersChild.add(input2DFilterA);
            }
        }
        return new ConvReluPooling2DNeuron(input2DFiltersChild);
    }

    @Override
    public Input2D apply(final Input2D input2D) {
        Input2D ret = null;
        for(final Input2DFilter input2DFilter: this.input2DFilters) {
            ret = input2DFilter.apply(input2D);
        }
        return ret;
    }

}

