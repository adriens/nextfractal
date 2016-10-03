<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-08-13 09:00:32</timestamp>
    <julia>true</julia>
    <point>-1.31</point>
    <point>0.0</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-1.5 - 1.5i,+1.5 + 1.5i] [x,n] {
		loop [0, 200] (mod2(x) &gt; 4) {
			x = x * x + w;
		}
	}
	color [#FF000000] {
		palette gradient {
			[#FFFFFFFF &gt; #FFFF0000, 50];
			[#FFFF0000 &gt; #FFFF0000, 200];
		}
		rule (n &gt; 0) [1] {
			gradient[n - 1]
		}
	}
}
</source>
    <time>0.0</time>
    <translation>0.0</translation>
    <translation>0.0</translation>
    <translation>1.0</translation>
    <translation>0.0</translation>
</mandelbrot>
