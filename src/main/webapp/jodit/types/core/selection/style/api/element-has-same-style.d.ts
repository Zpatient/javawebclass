/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { IStyle } from '../../../../types';
/**
 * Element has the same styles as in the commit
 * @private
 */
export declare function elementHasSameStyle(elm: Node, rules: IStyle): boolean;
/**
 * Element has the similar styles
 */
export declare function elementHasSameStyleKeys(elm: Node, rules: IStyle): boolean;
