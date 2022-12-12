/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/backspace
 */
import type { IJodit } from '../../../types';
/**
 * Check if two separate elements can be connected
 */
export declare function checkJoinNeighbors(jodit: IJodit, fakeNode: Node, backspace: boolean): boolean;
