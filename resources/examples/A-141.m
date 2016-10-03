<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-08-13 09:15:33</timestamp>
    <julia>false</julia>
    <point>-0.15333333333333332</point>
    <point>0.7849999999999999</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-2.5 - 1.5i,+0.5 + 1.5i] [x,n] {
		begin {
			wr = re(w);
			wi = im(w);
			tr = re(x);
			ti = im(x);
			xr = re(x);
			xi = im(x);
			ta = mod2(x);
			tb = atan2(xr,xi) * 2;
			zr = ta * cos(tb) + wr;
			zi = ta * sin(tb) + wi;
			x = &lt;zr - tr,zi - ti&gt;;
			d0 = mod2(x);
			l0 = 0;
		}
		loop [0, 200] (xr &gt; 1000 | xi &gt; 1000) {
			xr = re(x);
			xi = im(x);
			ta = mod2(x);
			tb = atan2(xr,xi) * 2;
			zr = ta * cos(tb) + wr;
			zi = ta * sin(tb) + wi;
			x = &lt;zr - tr,zi - ti&gt;;
			d1 = mod2(x);
			l1 = d1 / d0;
			d = abs(l1 - l0);
			if (d &lt; 0.000000001 | d &gt; 10000000000 | d1 &lt; 0.0000001) {
				stop;
			}
			d0 = d1;
			l0 = l1;
		}
	}
	color [(1,0,0,0)] {
		palette gradient {
			[#FFFF0A0A &gt; #FFFFFFFF, 20];
			[#FFFFFFFF &gt; #FF0042A9, 60];
			[#FF0042A9 &gt; #FF000000, 120];
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
