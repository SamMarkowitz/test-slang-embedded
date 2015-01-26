namespace: user.operations.utils

operations:
  - print:
      inputs:
        - text
      action:
        python_script: print text
      results:
        - SUCCESS