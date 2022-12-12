/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module helpers/utils
 */
import type { Nullable } from '../../../types';
/**
 * Reset Vanila JS native function
 * @example
 * ```js
 * reset('Array.from')(Set([1,2,3])) // [1, 2, 3]
 * ```
 */
export declare const reset: <T extends Function>(key: string) => Nullable<T>;