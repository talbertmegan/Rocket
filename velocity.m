%% recalculates velocity every __ seconds
import acceleration.*
Re = 6.371e6; %radius of the earth
p = [0,Re,0]; %position
v = [0,0,0]; % starting velocity will always be zero
a = [0,0,0]; %acceleration
thrust = [10,10,0]; %thrust
m = 10; %mass
dt = 0.1; %change in time in seconds // this is the default time for recalculating velocity ...
for t = 0:0.1:1.0
   v = findVelocity(v,thrust,p,dt)
   disp(v)
end
function futureVelocity = findVelocity(currentVelocity, thrust, p, dt)
    accel = acceleration(thrust, p);
    futureVelocity = currentVelocity + accel.*dt;
   % return currentVelocity;
end


