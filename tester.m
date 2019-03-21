

system = [...
        [0,-6.371e6,0],5.972e24, [0,0,0];... %earth
        [0,383.4e6,0],7.3276e22, [-1.022e3,0,0]... %moo
    ];

system = [system;system;system]

system = updateSystem(system)