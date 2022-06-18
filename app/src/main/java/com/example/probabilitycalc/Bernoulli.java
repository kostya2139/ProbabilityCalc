package com.example.probabilitycalc;

public class Bernoulli {
    public final double p, n;

    Bernoulli(double p, int n) throws Exception {
        this.p=p;
        if(n<0) throw new Exception("Bernoulli n must be natural");
        this.n=n;
    }

    public double calculatingFromTo(int a, int b) {
        double res=0;
        if(b-a < n/2) {
            for(int i=a; i<=b; ++i)
                res+=calculatingPrime(i);
        }
        else {
            for(int i=0; i<a; ++i)
                res+=calculatingPrime(i);
            for(int i=b+1; i<=n; ++i)
                res+=calculatingPrime(i);
            res=1-res;
        }
        return res;
    }

    public double calculatingPrime(double k) {
        if(k == 0 && p == 0) return 1;
        if(n == k && p == 1) return 1;
        return  Math.pow(p, k) * Math.pow(1-p, n-k) * binCoefficient(n, k);
    }

    private double binCoefficient(double n, double k) {
        double res=1, min=Math.min(k, n-k);
        for (int i=0; i<min; ++i) {
            res*=(n-i)/(i+1);
        }
        return res;
    }
}
