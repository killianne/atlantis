/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entity.Metric;
import java.util.List;

/**
 *
 * @author ahure
 */
public class ModelMetricMobile {
    
    private List<Metric> metrics;
    
    private List<MetricCalModel> metricCals;

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    public List<MetricCalModel> getMetricCals() {
        return metricCals;
    }

    public void setMetricCals(List<MetricCalModel> metricCals) {
        this.metricCals = metricCals;
    }
    
    
}
