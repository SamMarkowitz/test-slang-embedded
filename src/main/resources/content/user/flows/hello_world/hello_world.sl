namespace: user.flows.hello_world

imports:
  ops: user.operations.utils

flow:
  name: hello_world
  workflow:
    sayHi:
      do:
        ops.print:
          - text: "'Hello, World'"