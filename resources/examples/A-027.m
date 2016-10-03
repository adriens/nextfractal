<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-08-13 08:13:13</timestamp>
    <julia>false</julia>
    <point>0.3016666666666667</point>
    <point>0.025</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-3.5 - 1.5i,-0.5 + 1.5i] [x,n] {		
		loop [0, 200] (mod2(x) &gt; 40) {
			x = x * x + w;
		}
	}
	color [#FF000000] {
		palette gradient {
			[#FF000000 &gt; #FFFFFFFF, 100];
			[#FFFFFFFF &gt; #FF000000, 100];
		}
		init {
			z1 = mod(x);
			z2 = re(x);
		}
		rule (n &gt; 0) [1] {
			1,
			(1 + cos(z1 / 3 / pi)) / 2,
			(1 + sin(n * 1 / pi)) / 2,
			(1 + sin((n + z2) * 2 / pi)) / 2
		}
	}
}
</source>
    <time>0.0</time>
    <translation>1.0388462718600358</translation>
    <translation>0.09035654552693467</translation>
    <translation>1.144960154070011E-4</translation>
    <translation>0.0</translation>
</mandelbrot>
