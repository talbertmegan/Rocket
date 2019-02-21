%% recalculates velocity every __ seconds
Re = 6.371e6; %radius of the earth
p = [0,Re,0]; %position
StartingVelocity = [0,0,0]; % starting velocity will always be zero
a = [0,0,0]; %acceleration
%t = [10,10,0] %thrust
m = 10; %mass
t = 0.1; %time in seconds // this is the default time for recalculating velocity ...
v = findVelocity([0 1 1]);
function currentVelocity = findVelocity(currentVelocity)
    accel = findTotalAcceleration(0, p, m);
    currentVelocity = currentVelocity + accel.*t;
   % return currentVelocity;
end


