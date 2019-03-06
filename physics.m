% The beginning of the project
close all
clear all

    
%rocket properties
velocity = [0, 0, 0];
position = [0, 0, 0];
acceleration = [0, 0, 0]; %pre-init to reduce chance of errors
thrust = [0,1e3,0]; %rocket thrust


%n-body system properties, initial
% xposition, yposition, zposition, mass, xvelocity, yvelocity, zvelocity
system = [...
        [0,-6.371e6,0],5.972e24, [0,0,0];... %earth
        [0,383.4e6,0],7.3276e22, [-1.022e3,0,0]... %moo
    ];


%possible future additions (if someone else gets this project next year)
%airResistanceFactor = k
%angleOfAttack = theta
%liftFromAir = f(a,b,c)
%collisionboxes

% TODO add a wait for the game to begin otherwise the rocket will fall into earth at t=0    
% TODO add n-body system support

%General notes for the physics simulation
%startin position is set to be [0,0]
%all other calculations are based off this assumption
%so earth's starting position is [0,-6.371e6,0] (easier for n-body systems)
figure
set(gcf, 'WindowState', 'fullscreen')
startTime = clock;
c = 0;
timeFromStart = 0;
t1=clock;

 

while(timeFromStart<=100)
    % TODO this can be optimized by combining linear approximations later
    
    %loop start time
   
    timeFromStart = etime(t1, startTime);
    
    
    subplot(3,6,1)
title('P X');
subplot(3,6,2)
title('V X');
subplot(3,6,3)
title('A X');

subplot(3,6,7)
title('P Y');
subplot(3,6,8)
title('V Y');
subplot(3,6,9)
title('A Y');

subplot(3,6,13)
title('P Z');
subplot(3,6,14)
title('V Z');
subplot(3,6,15)
title('A Z');
    
        %if I remove any "hold on" the plots stop working!!
    subplot(3,6,1) %position x
    plot(timeFromStart, position(1), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,7) %position y
    plot(timeFromStart, position(2), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,13) %position z
    plot(timeFromStart, position(3), '.')
        xlim([0,100]);
        
    hold on
    subplot(3,6,2) %velocity x
    plot(timeFromStart, velocity(1), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,8) %velocity y
    plot(timeFromStart, velocity(2), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,14) %velocity z
    plot(timeFromStart, velocity(3), '.')
        xlim([0,100]);
    hold on
    
    hold on
    subplot(3,6,3) %acceleration x
    plot(timeFromStart, acceleration(1), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,9) %acceleration y
    plot(timeFromStart, acceleration(2), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,15) %acceleration z
    plot(timeFromStart, acceleration(3), '.')
        xlim([0,100]);
    hold on
    
    
    % system plotting: moon
    subplot(3,6,4) %pos x
        plot(timeFromStart, system(2,1), '.')
        title("M P X")
        xlim([0,100])
    hold on
    subplot(3,6,10) %pos y
        plot(timeFromStart, system(2,2), '.')
        title("M P Y")
        xlim([0,100])
        hold on
    subplot(3,6,16) %pos z
        plot(timeFromStart, system(2,3), '.')
        title("M P Z")
        xlim([0,100])
        hold on
    
    % system plotting: moon
    subplot(3,6,5) %pos x
        plot(timeFromStart, system(2,5), '.')
        title("M V X")
        xlim([0,100])
        hold on
    subplot(3,6,11) %pos y
        plot(timeFromStart, system(2,6), '.')
        title("M V Y")
        xlim([0,100])
        hold on
    subplot(3,6,17) %pos z
        plot(timeFromStart, system(2,7), '.')
        title("M V Z")
        xlim([0,100])
        hold on
    
    
    drawnow
    
    %hefty calculations
    system = updateSystem(system, finddt(t1)); 
    acceleration = findAcceleration(position,thrust,system);
    velocity = findVelocity(acceleration, velocity, finddt(t1));
    position = findPosition(velocity, position, finddt(t1));
    % p(t) = p(t-dt) + dt*p'(t-dt) is linear approximation, valid for
    % sufficiently small dt. p'(t-dt) = velocityVector
    
    
    t1 = clock;

    
    %{
    old graphing stuff
    position = findPosition(velocity, position, finddt(t1));
    subplot(2,2,1)
    plot(c, position,'.');
%     xlim([0 10]);
%     ylim([0 0.1]);
    hold on
    subplot(2,2,2);
    plot(c, velocity,'.');
%     xlim([0 10]);
%     ylim([0 3]);
    hold on
    subplot(2,2,3);
    plot(c, acceleration, '.');
%     fprintf('%d is acceleration \n',acceleration);
%     fprintf('%d is velocity \n',velocity);
%     fprintf('%d is position \n', position);
    %assume time between t2 assignment and t1 assignment is negligble
    %break;
    drawnow;
    hold on
    %}
    
   
end

function dt = finddt(t1)
    dt = etime(clock,t1);
end

