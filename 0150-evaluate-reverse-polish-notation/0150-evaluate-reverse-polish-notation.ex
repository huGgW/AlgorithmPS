defmodule Solution do
  @spec eval_rpn(tokens :: [String.t]) :: integer
  def eval_rpn(tokens) do
    eval_rpn_recursive(tokens, [])
  end

  @spec eval_rpn_recursive(tokens :: [String.t], stack :: [integer]) :: integer
  def eval_rpn_recursive(tokens, stack) do
    case tokens do
      [] ->
        [result] = stack
        result

      ["+" | tail] ->
        [b | [a | left]] = stack
        eval_rpn_recursive(tail, [a + b | left])

      ["-" | tail] ->
        [b | [a | left]] = stack
        eval_rpn_recursive(tail, [a - b | left])

      ["*" | tail] ->
        [b | [a | left]] = stack
        eval_rpn_recursive(tail, [a * b | left])

      ["/" | tail] ->
        [b | [a | left]] = stack
        eval_rpn_recursive(tail, [div(a, b) | left])

      [num_string | tail] ->
        eval_rpn_recursive(tail, [String.to_integer(num_string) | stack])
    end
  end
end