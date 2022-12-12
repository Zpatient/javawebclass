/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:core/decorators/persistent/README.md]]
 * @packageDocumentation
 * @module decorators/persistent
 */
import type { IComponent } from '../../../types';
export declare function persistent<T extends IComponent>(target: T, propertyKey: string): void;
