
package com.motorph.model;

/**
 *
 * @author Lenovo
 */
public class DeductionBreakdown {
    
    double sss;
    double philHealth;
    double pagIbig;
    double withholdingTax;

    public DeductionBreakdown(double sss, double philHealth, double pagIbig, double withholdingTax) {
        this.sss = sss;
        this.philHealth = philHealth;
        this.pagIbig = pagIbig;
        this.withholdingTax = withholdingTax;
    }
    
    
    public double getTotal() {
        return sss + philHealth + pagIbig + withholdingTax;
    }
    
}
