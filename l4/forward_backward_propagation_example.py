#compute the gradient of the funciton below using forward/backward propagation
#f(x,y) = [x + sigmoind(y)] / [sigmoid(x) + (x+y)^2]

import numpy as np
import math as math

def sigmoid(x):
    return 1/(1 + np.exp(-x))

def dsigmoid(x):
    return sigmoid(x)*(1-sigmoid(x))

dx=0
dy=0

x=3
y=-4
s0=sigmoid(y)
s1=sigmoid(x)
t1 = x + s0
t2 = x+y
t3=t2**2
t4 = s1 + t3
t5 = 1/t4
t6 = t1 * t5
print(t6)

dt6 = 1
dt5 = t1*dt6
dt1 = t5*dt6

dt4 = (-1/(t4**2)) * t5
dt3 = dt4
dt2=2*t2*dt3
dx += dt2
dy +=dt2
ds1=dt4
dx+=dsigmoid(x)*ds1
dx+=dt1
ds0=t1
dy+=dsigmoid(y)*ds0
print(dx,dy)

#=========================

x = 3 # example values
y = -4

# forward pass
sigy = 1.0 / (1 + math.exp(-y)) # sigmoid in numerator   #(1)
num = x + sigy # numerator                               #(2)
sigx = 1.0 / (1 + math.exp(-x)) # sigmoid in denominator #(3)
xpy = x + y                                              #(4)
xpysqr = xpy**2                                          #(5)
den = sigx + xpysqr # denominator                        #(6)
invden = 1.0 / den                                       #(7)
f = num * invden # done!

# backprop f = num * invden
dnum = invden # gradient on numerator                             #(8)
dinvden = num                                                     #(8)
# backprop invden = 1.0 / den
dden = (-1.0 / (den**2)) * dinvden                                #(7)
# backprop den = sigx + xpysqr
dsigx = (1) * dden                                                #(6)
dxpysqr = (1) * dden                                              #(6)
# backprop xpysqr = xpy**2
dxpy = (2 * xpy) * dxpysqr                                        #(5)
# backprop xpy = x + y
dx = (1) * dxpy                                                   #(4)
dy = (1) * dxpy                                                   #(4)
# backprop sigx = 1.0 / (1 + math.exp(-x))
dx += ((1 - sigx) * sigx) * dsigx # Notice += !! See notes below  #(3)
# backprop num = x + sigy
dx += (1) * dnum                                                  #(2)
dsigy = (1) * dnum                                                #(2)
# backprop sigy = 1.0 / (1 + math.exp(-y))
dy += ((1 - sigy) * sigy) * dsigy                                 #(1)
# done! phew
print(dx,dy)