import React, { useState } from 'react';

export default function Counter() {
  const [counter, setCounter] = useState(0);

  return (
    <div>
      <p> This is the Counter </p>
      <p>{counter}</p>
      <button onClick={() => setCounter(counter + 2)}>Increase Counter </button>
    </div>
  );
}
