fractal {
	orbit [<-2.5,-1.5>,<0.5,1.5>] [x,n] {
		loop [0, 200] (mod2(x) > 4) {
			x = x * x + w;
		}
	}
	color [(1,0,0,0)] {
		rule (n = 0) [1.0] {
			1,0,0,0
		}
		rule (re(x) > 0) [1.0] {
			1,1,1,1
		}
	}
}
