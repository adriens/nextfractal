<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-08-13 23:05:10</timestamp>
    <julia>true</julia>
    <point>0.9555508968274993</point>
    <point>0.32632835553753603</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-1.5 - 1.5i,+1.5 + 1.5i] [x,n,z] {
		trap circle0 [&lt;1,0.2&gt;] {
			MOVETO(&lt;2,0&gt;);
			ARCTO(&lt;2,2&gt;,&lt;0,2&gt;);
			ARCTO(&lt;-2,2&gt;,&lt;-2,0&gt;);
			ARCTO(&lt;-2,-2&gt;,&lt;0,-2&gt;);
			ARCTO(&lt;2,-2&gt;,&lt;2,0&gt;);
		}
		begin {
			mmax = 0;
			z = 0;
			if (~julia) {
				x = &lt;pi / 2,0&gt;;
			}
		}
		loop [0, 200] (abs(im(x)) &gt; 100) {
			xr = re(x);
			xi = im(x);
			wr = re(w);
			wi = im(w);
			ta = cos(xr);
			tb = sin(xr);
			tc = exp(+xi);
			td = exp(-xi);
			te = 0.5 * tb * (tc + td);
			tf = 0.5 * ta * (tc - td);
			zr = wr * te - wi * tf;
			zi = wi * te + wr * tf;			
			x = &lt;zr,zi&gt;;
			m = mod(x);
			if (m &gt; mmax) {
				mmax = m;
				z = x;
			}
			if (mod2(x) &lt; 0.0000000001) {
				stop;
			}
			if (circle0 ~? x) {
				stop;
			}
		}
	}
	color [(1,0,0,0)] {
		palette gradient0 {
			[#FFFF0000 &gt; #FFFFFF00, 20];
			[#FFFFFF00 &gt; #FFFFFFFF, 90];
			[#FFFFFFFF &gt; #FF000000, 90];
		}
		palette gradient1 {
			[#FF000088 &gt; #FFFFFF00, 40];
			[#FFFFFF00 &gt; #FFFFFFFF, 80];
			[#FFFFFFFF &gt; #FF000000, 80];
		}
		init {
			m = mod(x);
			p = atan2(re(z), im(z)) / pi;
			if (p &lt; 0) {
				p = p + 1;
			}
			p = 140 * p;
			q = 199 * (m - floor(m));
		}
		rule (n &gt; 0) [1] {
			gradient0[q]
		}
		rule (n &gt; 0) [0.9] {
			gradient1[p]
		}
}
}
</source>
    <time>0.0</time>
    <translation>0.9845718689444042</translation>
    <translation>0.923355823072793</translation>
    <translation>0.3255713057967823</translation>
    <translation>0.0</translation>
</mandelbrot>
