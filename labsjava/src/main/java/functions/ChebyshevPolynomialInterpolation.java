package functions;

public class ChebyshevPolynomialInterpolation implements MathFunction {
    private final MathFunction function;
    private final double[] nodes;

    public ChebyshevPolynomialInterpolation(MathFunction function, double a, double b, int n) {
        this.function = function;

        nodes = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] =  (b - a) / 2 * Math.cos(Math.PI * (2 * i + 1) / (2 * n + 2)) + (a + b) / 2;
        }
    }

    @Override
    public double apply(double x) {
        double result = 0;
        for (int j = 0; j < nodes.length; j++) {
            double lagrangeBasisPolynomial = function.apply(nodes[j]);
            for (int k = 0; k < nodes.length; k++) {
                if (k != j) {
                    lagrangeBasisPolynomial *= (x - nodes[k]) / (nodes[j] - nodes[k]);
                }
            }
            result += lagrangeBasisPolynomial;
        }
        return result;
    }
}