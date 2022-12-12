/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:core/plugin/README.md]]
 * @packageDocumentation
 * @module plugin
 */
import type { IJodit, IPlugin, IViewBased } from '../../types';
import { ViewComponent } from '../component';
export declare abstract class Plugin<T extends IViewBased = IJodit> extends ViewComponent<T> implements IPlugin<T> {
    requires: string[];
    /** @override */
    buttons: IPlugin['buttons'];
    /**
     * Plugin have CSS style and it should be loaded
     */
    hasStyle: boolean;
    /** @override */
    className(): string;
    protected abstract afterInit(jodit: T): void;
    protected abstract beforeDestruct(jodit: T): void;
    constructor(jodit: T);
    init(jodit: T): void;
    destruct(): void;
}
