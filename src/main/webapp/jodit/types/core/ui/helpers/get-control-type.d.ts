/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module ui
 */
import type { CanUndef, Controls, IControlType, IControlTypeStrong } from '../../../types';
/**
 * Get control for button name
 */
export declare function getControlType(button: IControlType | string, controls: CanUndef<Controls>): IControlTypeStrong;
export declare function findControlType(path: string, controls: Controls): IControlTypeStrong | void;
