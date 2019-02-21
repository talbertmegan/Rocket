%%Handles acceleration




%define constants
G = 6.67e-11; %Gravitational constant
Re = 6.371e6; %radius of the earth
Me = 5.972e24; %mass of the earth

%define variables
p = [0,Re,0]; %position
v = [0,0,0]; %velocity
a = [0,0,0]; %acceleration
t = [10,10,0] %thrust
m = 10; %mass

for ii = [0:100]
    break
    findTotalAcceleration(t,p,m)
end



function a = findGravitationalAcceleration(position, mass)
    global G Re Me
    radiusLength = sqrt( sum ( position.^2));
    radiusLength
    radiusUnit = position./radiusLength;
    -G * Me * mass / radiusLength^2
    a=0
end

function a = findTotalAcceleration(thrust, position, mass)
    a = thrust + findGravitationalAcceleration(position, mass)
end