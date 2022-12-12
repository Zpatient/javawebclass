/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/enter
 */
import type { IJodit } from '../../../types';
/**
 * Checks the possibility and necessity of inserting a BR instead of a block
 * @private
 */
export declare function checkBR(jodit: IJodit, current: Node, shiftKeyPressed?: boolean): boolean;