import { createLogger } from 'redux-logger';

/* eslint-disable */
const actionTransformer = (action: any) => {
  const { type } = action;
  if (type === 'BATCHING_REDUCER.BATCH') {
    action.payload.type = action.payload.map((next: any) => next.type).join(' => ');
    return action.payload;
  }

  return action;
};

const level = 'info';

const logger = {};

for (const method in console) {
  if (typeof console[method] === 'function') {
    logger[method] = console[method].bind(console);
  }
}

logger[level] = function levelFn(...args) {
  const lastArg = args.pop();

  if (Array.isArray(lastArg)) {
    return lastArg.forEach((item) => {
      console[level].apply(console, [...args, item]);
    });
  }

  console[level].apply(console, arguments);
};

const loggingMiddleware = (store) => (next) => (action) => {
  // Our middleware
  console.log(store);
  const state = store.getState();
  console.log(state);
  console.log(action);
  next(action);
};

export const dva = {
  config: {
    onError(err: ErrorEvent) {
      err.preventDefault();
      console.error(err.message);
    },
    onAction: [
      createLogger({
        level,
        actionTransformer,
        logger,
      }),
      loggingMiddleware,
    ],
  },
};
